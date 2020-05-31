/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvilleda.controller;

import com.dvilleda.model.Compte;
import com.dvilleda.model.Compte_Reunion;
import com.dvilleda.model.Contenu;
import com.dvilleda.model.Dossier;
import com.dvilleda.model.PointOrdre;
import com.dvilleda.model.Reunion;
import com.dvilleda.services.CompteService;
import com.dvilleda.services.ContenuService;
import com.dvilleda.services.DossierService;
import com.dvilleda.services.PointService;
import com.dvilleda.services.ReunionService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Danny Alexander Villeda
 */
@Controller
@SessionAttributes({"username", "role", "id_compte"})
public class GestionController {

    private CompteService compteService;
    private ReunionService reunionService;
    private PointService pointService;
    private DossierService dossierService;
    private ContenuService contenuService;

    public void setCompteService(CompteService compteService) {
        this.compteService = compteService;
    }

    public void setReunionService(ReunionService reunionService) {
        this.reunionService = reunionService;
    }

    public void setPointService(PointService pointService) {
        this.pointService = pointService;
    }

    public void setDossierService(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    public void setContenuService(ContenuService contenuService) {
        this.contenuService = contenuService;
    }

    //@ResponseBody
    @RequestMapping("/")
    public String acceuil(ModelMap model) {
        return "index";
    }

    @RequestMapping("home")
    public String home(ModelMap model) {
        return "index";
    }

    @RequestMapping("calendrier")
    public String calendrier(ModelMap model, HttpSession session) {
        String idcompte = String.valueOf(session.getAttribute("id_compte"));
        List<Reunion> liste = this.reunionService.getReunionsCompte(idcompte);
        List<Compte_Reunion> status = this.reunionService.getStatusReunion(idcompte);
        model.addAttribute("reunions", liste);
        model.addAttribute("status", status);

        return "calendrier";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "changerstatus", params = {"idcompte", "idreunion", "status"})
    public String changerStatus(@RequestParam("idcompte") int id, @RequestParam("idreunion") int i, @RequestParam("status") String status, ModelMap model) {
        List<Object> list = new ArrayList<>();
        if (status.equals("Present")) {
            list.add("Absent");
            list.add(id);
            list.add(i);
        } else if (status.equals("Absent")) {
            list.add("Present");
            list.add(id);
            list.add(i);
        }
        this.reunionService.changerStatus(list);
        return "redirect:/calendrier";
    }

    @RequestMapping("dossier")
    public String dossier(ModelMap model) {
        List<Dossier> liste = this.dossierService.getDossiers();
        model.addAttribute("dossier", liste);
        return "dossier";
    }

    @RequestMapping(value = "VoirDossier", method = RequestMethod.GET)
    public String voirDossier(ModelMap model) throws IOException {
        model.addAttribute("dossiers", this.dossierService.getDossiers());
        model.put("dossierForm", new DossierForm());
        return "contenudossier";
    }

    @RequestMapping(value = "VoirDossier", method = RequestMethod.POST)
    public String voirDossier(HttpSession session, DossierForm dossierForm, ModelMap model) throws IOException {
        model.addAttribute("dossiers", this.dossierService.getDossiers());
        String titreform = dossierForm.getTitre();
        Dossier dossier = this.dossierService.getDossier(titreform);
        if (dossier == null) {
            model.put("message", "Dossier (" + titreform + ") introuvable");
        } else {
            model.addAttribute("listecontenu", this.contenuService.findByIdDossier(dossier.getId()));
            model.addAttribute("dossier", dossier);
        }
        return "contenudossier";
    }

    @RequestMapping("creerDossier")
    public String creerdossier(ModelMap model) {
        model.put("dossierForm", new DossierForm());
        model.addAttribute("newdossier", "Oui");
        return "creerdossier";
    }

    @RequestMapping(method = RequestMethod.GET, value = "changerdossier", params = {"id"})
    public String changerdossier(@RequestParam("id") int id, ModelMap model) {
        model.put("dossierFormModif", new DossierForm());
        Dossier dossier;
        dossier = this.dossierService.getDossierID(id);
        model.addAttribute("dossierTrouve", dossier);
        return "creerdossier";

    }

    @RequestMapping(value = "/changerdossier/ModifierDossier", method = RequestMethod.POST)
    public String changerdossier(HttpSession session, DossierForm dossierFormModif, ModelMap model) throws IOException {
        Dossier dossier = new Dossier();
        boolean resultat;
        dossier.setId(dossierFormModif.getId());
        dossier.setTitre(dossierFormModif.getTitre());
        dossier.setEstActif(dossierFormModif.isEstActif());

        resultat = dossierService.setDossier(dossier);
        if (resultat == false) {
            model.put("message", "Informations du dossier invalide");
            return "creerdossier";
        }
        return "redirect:/dossier";
    }

    @RequestMapping(value = "CreerDossier", method = RequestMethod.POST)
    public String creerdossier(HttpSession session, DossierForm dossierForm, ModelMap model) throws IOException {
        Dossier dossier = new Dossier();
        boolean resultat;
        dossier.setTitre(dossierForm.getTitre());
        dossier.setEstActif(dossierForm.isEstActif());

        resultat = dossierService.creerDossier(dossier);
        if (resultat == false) {
            model.put("message", "Informations du dossier invalides. Dossier non cree");
            return "creerdossier";
        }
        return "dossier";
    }

    @RequestMapping(method = RequestMethod.GET, value = "ajoutercontenu", params = {"id"})
    public String ecrireDansDossier(@RequestParam("id") int id, ModelMap model) {
        model.put("contenuFormcreer", new ContenuForm());
        model.addAttribute("idreunion", id);

        return "inserercontenudossier";
    }

    @RequestMapping(value = "ajoutercontenu/ecriredansdossier", method = RequestMethod.POST)
    public String setContenu(HttpSession session, ContenuForm contenuFormcreer, ModelMap model) throws IOException {
        boolean resultat;
        Contenu contenu = new Contenu();
        contenu.setContenu(contenuFormcreer.getTexte());
        contenu.setId_dossier(contenuFormcreer.getId());
        resultat = this.contenuService.creerContenu(contenu);

        if (resultat == false) {
            model.put("message", "Modifications non apportes");
            return "inserercontenudossier";
        }
        return "redirect:/dossier";
    }

    @RequestMapping("reunion")
    public String reunion(ModelMap model, HttpSession session) {
        String idcompte = String.valueOf(session.getAttribute("id_compte"));
        List<Reunion> liste = this.reunionService.getReunionsCompte(idcompte);
        model.addAttribute("reunions", liste);
        return "reunion";
    }

    @RequestMapping("creerreunion")
    public String changerreunion(ModelMap model) {
        String creation = "Creer";
        model.put("reunionForm", new ReunionForm());
        model.addAttribute("Creer", creation);
        return "creerreunion";
    }

    @RequestMapping(method = RequestMethod.GET, value = "changerreunion", params = {"nom"})
    public String changerreunion(@RequestParam("nom") String nom, ModelMap model) {
        model.put("reunionFormModif", new ReunionForm());
        Reunion reunion;
        reunion = this.reunionService.getReunion(nom);
        model.addAttribute("reunionTrouve", reunion);
        return "creerreunion";

    }

    @RequestMapping(value = "/changerreunion/ModifierReunion", method = RequestMethod.POST)
    public String changerreunion(HttpSession session, @Valid @ModelAttribute("reunionFormModif") ReunionForm reunionFormModif, BindingResult result, ModelMap model) throws IOException {
            Reunion uneReunion = new Reunion();
            boolean resultat;
            uneReunion.setDuree(reunionFormModif.getDuree());
            uneReunion.setTitre(reunionFormModif.getTitre());
            uneReunion.setDate(reunionFormModif.getDate().atTime(reunionFormModif.getHeure(), reunionFormModif.getMinute()));
            uneReunion.setReunion_ouverte(reunionFormModif.isReunion_ouverte());

            resultat = reunionService.setReunion(uneReunion);
            if (resultat == false) {
                model.put("message", "Informations de la reunion invalide");
                return "creerreunion";
            }
            return "redirect:/reunion";
    }

    @RequestMapping(value = "CreerReunion", method = RequestMethod.POST)
    public String creerreunion(HttpSession session, ReunionForm reunionForm, ModelMap model) throws IOException {
        Reunion uneReunion = new Reunion();
        boolean resultat;
        uneReunion.setDuree(reunionForm.getDuree());
        uneReunion.setTitre(reunionForm.getTitre());
        uneReunion.setDate(reunionForm.getDate().atTime(reunionForm.getHeure(), reunionForm.getMinute()));
        uneReunion.setReunion_ouverte(reunionForm.isReunion_ouverte());

        resultat = reunionService.creerReunion(uneReunion);
        if (resultat == false) {
            model.put("message", "Informations de la reunion invalide");
            return "creerreunion";
        }
        return "reunion";
    }

    @RequestMapping(value = "VoirReunion", method = RequestMethod.GET)
    public String voirreunion(ModelMap model) throws IOException {
        model.addAttribute("reunions", this.reunionService.getReunions());
        model.put("reunionForm", new ReunionForm());
        return "voirreunion";
    }

    @RequestMapping(value = "VoirReunion", method = RequestMethod.POST)
    public String voirreunion(HttpSession session, ReunionForm reunionForm, ModelMap model) throws IOException {
        model.addAttribute("reunions", this.reunionService.getReunions());
        String titreform = reunionForm.getTitre();
        Reunion reunion = this.reunionService.getReunion(titreform);
        if (reunion == null) {
            model.put("message", "Reunion (" + titreform + ") introuvable");
        } else {
            model.addAttribute("reunion", reunion);
        }
        return "voirreunion";
    }

    @RequestMapping(method = RequestMethod.GET, value = "annuler/", params = {"id"})
    public String deleteReunion(@RequestParam("id") int id, ModelMap model) {
        Reunion reunion = new Reunion();
        reunion.setId(id);
        this.reunionService.deleteReunion(reunion);
        return "redirect:/VoirReunion";
    }

    @RequestMapping("changerdossier/dossier")
    public View retourDossier(ModelMap model) {
        return new RedirectView("/dossier", true, false);
    }

    @RequestMapping("inserercontenu/VoirReunion")
    public View retourReunion(ModelMap model) {
        return new RedirectView("/VoirReunion", true, false);
    }

    @RequestMapping("ajoutercontenu/dossier")
    public View retourDossierID(ModelMap model) {
        return new RedirectView("/dossier", true, false);
    }

    @RequestMapping("ajouterpoint/VoirReunion")
    public View retourPoint(ModelMap model) {
        return new RedirectView("/VoirReunion", true, false);
    }

    @RequestMapping("changerreunion/reunion")
    public View retourchanger(ModelMap model) {
        return new RedirectView("/reunion", true, false);
    }

    @RequestMapping("voircompterendu/reunion")
    public View retourpopint(ModelMap model) {
        return new RedirectView("/reunion", true, false);
    }

    @RequestMapping(method = RequestMethod.GET, value = "ajouterpoint", params = {"id"})
    public String creerPoint(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("reunion", this.pointService.findByIdReunion(id));
        model.put("contenuFormcreer", new ContenuForm());
        model.addAttribute("idreunion", id);
        return "inserercontenu";
    }

    @RequestMapping(method = RequestMethod.GET, value = "inserercontenu", params = {"id"})
    public String ecrireDansReunion(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("reunion", this.pointService.findByIdReunion(id));
        return "inserercontenu";
    }

    @RequestMapping(method = RequestMethod.GET, value = "inserercontenu", params = {"nom"})
    public String getPoint(@RequestParam("nom") String nom, ModelMap model) {
        PointOrdre point;
        point = this.pointService.getPoint(nom);
        model.put("contenuForm", new ContenuForm());
        model.addAttribute("message", point.getNom());
        model.addAttribute("contenu", point.getDescription());
        return "inserercontenu";
    }

    @RequestMapping(method = RequestMethod.GET, value = "inserercontenu/deletepoint/", params = {"id"})
    public String deletePoint(@RequestParam("id") int id, ModelMap model) {
        PointOrdre point = new PointOrdre();
        point.setId(id);
        this.pointService.deletePoint(point);
        return "redirect:/VoirReunion";
    }

    @RequestMapping(method = RequestMethod.GET, value = "voircompterendu", params = {"id"})
    public String voirPoints(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("points", this.pointService.findByIdReunion(id));
        model.addAttribute("reunion", this.reunionService.getReunionID(id));
        return "voircompterendu";
    }

    @RequestMapping(value = "InsererContenu", method = RequestMethod.POST)
    public String setPoint(HttpSession session, ContenuForm contenuForm, ModelMap model) throws IOException {
        boolean resultat;
        model.addAttribute("reunion", this.pointService.findByIdReunion(1));
        PointOrdre point = new PointOrdre();
        point.setNom(contenuForm.getNom());
        point.setDescription(contenuForm.getTexte());
        resultat = this.pointService.setContenu(point);

        if (resultat == false) {
            model.put("message", "Modifications non apportes");
            return "inserercontenu";
        }
        return "reunion";
    }

    @RequestMapping(value = "ajouterpoint/CreerContenu", method = RequestMethod.POST)
    public String creerPoint(HttpSession session, ContenuForm contenuFormcreer, ModelMap model) throws IOException {
        boolean resultat;
        PointOrdre point = new PointOrdre();
        point.setNom(contenuFormcreer.getNom());
        point.setDescription(contenuFormcreer.getTexte());
        point.setReunion_id(contenuFormcreer.getId());
        resultat = this.pointService.creerpoint(point);

        if (resultat == false) {
            model.put("error", "Modifications non apportes");
            return "inserercontenu";
        }
        return "redirect:/reunion";
    }

    @RequestMapping(value = "deconnexion", method = RequestMethod.GET)
    public String deconnexion(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.removeAttribute("role");
        session.removeAttribute("username");
        session.invalidate();
        return "index";
    }

    @RequestMapping(value = "connection", method = RequestMethod.GET)
    public String connection(ModelMap model) {
        model.put("form", new Form());
        return "connection";
    }

    @RequestMapping(value = "connection", method = RequestMethod.POST)
    public String connect(HttpSession session, @Valid @ModelAttribute("form") Form form, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors()) {
            return "connection";
        } else {
            Compte compte = new Compte();
            compte = compteService.findCompte(form.getUsername(), form.getPassword());
            if (compte != null) {
                session.setAttribute("username", compte.getUser());
                session.setAttribute("role", compte.getNiveau());
                session.setAttribute("id_compte", compte.getId());
            } else {
                model.put("message", "Informations de compte invalides");
                return "connection";

            }
            return "index";
        }
    }

    @RequestMapping(value = "creercompte", method = RequestMethod.GET)
    public String creation(ModelMap model) {
        model.put("form", new Form());
        return "creercompte";
    }

    @RequestMapping(value = "creercompte", method = RequestMethod.POST)
    public String creer(HttpSession session, Form form, ModelMap model) throws IOException {
        boolean resultat;
        resultat = compteService.creerCompte(form.getUsername(), form.getPassword());
        if (resultat == false) {
            model.put("message", "Nom d'usager invalide");
            return "creercompte";
        }
        return "index";
    }

    public static class Form {

        @NotNull
        @Size(min = 1, max = 30, message = "Erreur")
        private String username = "";
        @NotNull
        @Size(min = 5, max = 50)
        private String password = "";

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class ReunionForm {

        private int id;
        @NotNull
        @Size(min = 5, max = 50)
        private String titre = "";
        @NotNull
        private LocalDate date;
        @NotNull
        @Min(value = 1, message = "Nombre non valide")
        private int duree;
        @NotNull
        @Min(value = 1, message = "Nombre non valide (1-24)")
        @Max(value = 24, message = "Nombre non valide (1-24)")
        private int heure;
        @NotNull
        @Min(value = 0, message = "Nombre non valide (0-60)")
        @Max(value = 0, message = "Nombre non valide (0-60)")
        private int minute;
        private boolean reunion_ouverte;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getDuree() {
            return duree;
        }

        public void setDuree(int duree) {
            this.duree = duree;
        }

        public boolean isReunion_ouverte() {
            return reunion_ouverte;
        }

        public void setReunion_ouverte(boolean reunion_ouverte) {
            this.reunion_ouverte = reunion_ouverte;
        }

        public int getHeure() {
            return heure;
        }

        public void setHeure(int heure) {
            this.heure = heure;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }
    }

    public static class ContenuForm {

        @NotNull
        @Size(min = 5, max = 50)
        private String nom;
        @NotNull
        @Size(min = 5, max = 50)
        private String texte;
        @NotNull
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getTexte() {
            return texte;
        }

        public void setTexte(String texte) {
            this.texte = texte;
        }
    }

    public static class DossierForm {

        @NotNull
        private int id;
        @NotNull
        @Size(min = 5, max = 50)
        private String titre = "";
        private boolean estActif;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public boolean isEstActif() {
            return estActif;
        }

        public void setEstActif(boolean estActif) {
            this.estActif = estActif;
        }

    }
}
