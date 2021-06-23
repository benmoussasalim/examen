package com.ant.examen.services;

import com.ant.examen.responses.ChartResponse;

public interface ChartService {
    public ChartResponse countEntrepriseByEtat();
    public ChartResponse countCandidatByEtat();
    public ChartResponse countQuestionExamenByTheme();
    public ChartResponse countExamenParticipationByEntreprise(Integer id);
    public ChartResponse countInvitationByEntreprise(Integer id);
    public  ChartResponse countMentionByEntreprise(Integer id);

    public ChartResponse countInvitationByCandidat(Integer id);
    public  ChartResponse countMentionByCandidat(Integer id);
}
