package com.social.services;

import com.social.database.RequirementDBQueries;
import com.social.model.Donor;
import com.social.model.Profile;
import com.social.model.Requirement;

import java.util.List;

/**
 * Created by niranjan.agrawal on 2/12/16.
 */
public class RequirementDAOImpl implements RequirementDAO {

    @Override
    public List<Requirement> getAllRequirements(long ngoId) {
        return RequirementDBQueries.getAllRequirementsForNGO(ngoId);
    }

    @Override
    public List<Requirement> getAllRequirementsForStatus(long ngoId, String status) {
        return RequirementDBQueries.getAllRequirementsForStatus(ngoId,status);
    }

    @Override
    public List<Donor> getAllDonorsForRequirementId(long ngoId, long reqId) {
        return RequirementDBQueries.getAllDonorsForRequirementId(ngoId,reqId);
    }

    @Override
    public Requirement getRequirement(long ngoId,long requirementId) {
        return RequirementDBQueries.getRequirementForId(ngoId,requirementId);
    }

    @Override
    public Requirement addRequirement(long ngoId,Requirement requirement) {

        return RequirementDBQueries.addRequirementForNGO(ngoId,requirement);
    }

    @Override
    public Requirement updateRequirement(long ngoId,long reqId,Requirement requirement) {

        return RequirementDBQueries.updateRequirementForId(ngoId,reqId,requirement);
    }

    @Override
    public Requirement removeRequirement(long ngoId,long requirementId) {
        return null;
    }
}
