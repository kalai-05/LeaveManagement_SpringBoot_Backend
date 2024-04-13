package com.leave.LeaveManagement.service;

public class EmpNotFoundEx  extends RuntimeException{
    public EmpNotFoundEx (Long id){
        super("This Id Employee not here "+id);
    }
}
