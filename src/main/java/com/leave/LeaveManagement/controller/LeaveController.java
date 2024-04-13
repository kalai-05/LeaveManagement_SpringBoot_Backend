package com.leave.LeaveManagement.controller;


import com.leave.LeaveManagement.model.LeaveDetails;
import com.leave.LeaveManagement.repository.LeaveRepo;
import com.leave.LeaveManagement.service.EmpNotFoundEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveController {

    @Autowired
    private LeaveRepo leaveRepository;


    //add leave employee details into database
    @PostMapping("/leave")
    LeaveDetails details (@RequestBody LeaveDetails details){
        return  leaveRepository.save(details);
    }


    //get all leave employee details from the database
    @GetMapping("/allDetails")
    List<LeaveDetails>getAllDetails(){
        return leaveRepository.findAll();
    }


    //get leave employee by id
    @GetMapping("/leave/{id}")
    LeaveDetails details(@PathVariable Long id){
        return leaveRepository.findById(id).orElseThrow(() ->new  EmpNotFoundEx(id));
    }

    //edit the Employee details using by given employee id
    @PutMapping("/leave/{id}")
    LeaveDetails updateDetails(@RequestBody LeaveDetails updateDetails,@PathVariable Long id){
        return  leaveRepository.findById(id)
                .map(leave ->{
                    leave.setEmpName(updateDetails.getEmpName());
                    leave.setDepartment(updateDetails.getDepartment());
                    leave.setLeaveDate(updateDetails.getLeaveDate());
                    leave.setReason(updateDetails.getReason());
                    return leaveRepository.save(leave);

                }).orElseThrow(()-> new EmpNotFoundEx(id));
    }

    //delete the leave employee details  by using id
    @DeleteMapping("/leave/{id}")
    String delateLeaveEmp(@PathVariable Long id){
        if(!leaveRepository.existsById(id)){
            throw  new EmpNotFoundEx(id);

        }
        return  "Employee   "+ id     + " has deleted ...!";
    }



}
