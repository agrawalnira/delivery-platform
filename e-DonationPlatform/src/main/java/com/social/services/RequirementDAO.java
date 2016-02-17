package com.social.services;

import com.social.model.Donor;
import com.social.model.Profile;
import com.social.model.Requirement;

import java.util.List;

/**
 * Created by niranjan.agrawal on 2/12/16.
 */
public interface RequirementDAO {

    public List<Requirement> getAllRequirements(long ngoId);

    public List<Requirement> getAllRequirementsForStatus(long ngoId, String status);

    public List<Donor> getAllDonorsForRequirementId(long ngoId, long reqId);

    public Requirement getRequirement(long ngoId,long requirementId);

    public Requirement addRequirement(long ngoId,Requirement requirement);

    public Requirement updateRequirement(long ngoId,long reqId,Requirement requirement);

    public Requirement removeRequirement(long ngoId,long requirementId);

}
