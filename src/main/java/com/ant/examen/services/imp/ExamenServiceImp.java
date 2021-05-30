package com.ant.examen.services.imp;

import com.ant.examen.dto.ExamenResponse;
import com.ant.examen.dto.FilterRequest;
import com.ant.examen.dto.ImageResponse;
import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Entreprise;
import com.ant.examen.entities.Examen;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import com.ant.examen.repository.ExamenRepository;
import com.ant.examen.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ExamenServiceImp implements ExamenService {
    @Value("${upload-dir}")
    private String uploadDirectory;
    @Autowired
    private ExamenRepository examenRepository;
    @Override
    public MessageResponse save(Examen examen) {
        examen.setDateCreation(new Date());
        examenRepository.save(examen);
        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse delete(Integer id) {
        boolean exist = examenRepository.
                existsByIdAndParticipationsIsNotNull(id);
        if (exist) {
            return new MessageResponse(false, "Attention",
                    "Candidat affecté a un ou plusieurs formations");
        }


        examenRepository.deleteById(id);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse update(Examen examen) {
        boolean exist = examenRepository.existsByIdAndParticipationsIsNotNull(examen.getId());
        if (exist) {
            return new MessageResponse(false, "Attention",
                    "Candidat affecté a un ou plusieurs formations");
        }


        examenRepository.save(examen);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public List<Examen> findAll() {
        return examenRepository.findAll();
    }

    @Override
    public List<Examen> findByEntreprise(Integer id) {
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return examenRepository.findByEntreprise(entreprise);
    }

    @Override
    public Examen findById(Integer id) {
        return examenRepository.getById(id);
    }

    @Override
    public List<Examen> findByTheme(Integer id) {
        Theme theme = new Theme();
        theme.setId(id);
        return examenRepository.findByTheme(theme);
    }

    @Override
    public List<Examen> findByDateExpiration(Date dataExpiration) {
        return examenRepository.findByDateExpiration(dataExpiration);
    }

    @Override
    public List<ExamenResponse> findNotExpired(FilterRequest filterRequest) {


        Specification<Examen> specification = new Specification<Examen>() {

            @Override
            public Predicate toPredicate(Root<Examen> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if (filterRequest.getLibelle()!=null && !filterRequest.getLibelle().isEmpty()) {
                    predicates.add(criteriaBuilder.like((root.get("libelle")), "%" + filterRequest.getLibelle()+ "%"));
                }

                if (filterRequest.getTheme() !=null) {
                    predicates.add(criteriaBuilder.equal(root.get("theme"), filterRequest.getTheme()));
                }

                if (filterRequest.getEntreprise() !=null) {
                    predicates.add(criteriaBuilder.equal(root.get("entreprise"), filterRequest.getEntreprise()));
                }
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateExpiration"), new Date()));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }

        };
        
        
       List<Examen>examens = examenRepository.findAll(specification);
        List<ExamenResponse> examenResponses = new ArrayList<>();
        for (Examen exam: examens){
            ExamenResponse examenResponse = new ExamenResponse();
            examenResponse.setExamen(exam);

            if(exam.getEntreprise().getImage()!=null) {

                Path rootLocation = Paths.get(uploadDirectory);

                Path file = rootLocation.resolve(exam.getEntreprise().getImage());
                try {
                    byte[] imgByte = Files.readAllBytes(file);
                    examenResponse.setPicture(imgByte);


                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // resources.add(resource);
            }
            examenResponses.add(examenResponse);
        }
        return examenResponses;
    }
}

