/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.service;

import fr.isima.rdvmed.entity.Patients;
import fr.isima.rdvmed.ejb.PatientsEJB;
import fr.isima.rdvmed.entity.Rdv;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vagrant
 */
@Path("patients")
public class PatientsFacadeREST {

    @EJB
    private PatientsEJB patients;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Patients create(Patients entity) {
        return patients.create(entity);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Patients edit(@PathParam("id") Short id, Patients entity) {
        entity.setId(id);
        return patients.edit(entity);
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response remove(@PathParam("id") Short id) {
        Response r = Response.notModified().build();
        if(patients.remove(id))
            r = Response.ok().build();
        return r;
    }

    @GET
    @Path("rdv/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Rdv> rdv(@PathParam("id") Short id) {
        Patients p = patients.find(id);
        Collection<Rdv> rdv = null;
        if(p!=null)
            rdv = p.getRdvCollection();
        return rdv;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Patients find(@PathParam("id") Short id) {
        return patients.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Patients> findAll() {
        return patients.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Patients> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return patients.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(patients.count());
    }
    
}
