package com.leave.LeaveManagement.repository;

import com.leave.LeaveManagement.model.LeaveDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepo extends JpaRepository<LeaveDetails,Long> {
}
