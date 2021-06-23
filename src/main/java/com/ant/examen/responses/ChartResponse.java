package com.ant.examen.responses;

import lombok.Data;

import java.util.List;

@Data
public class ChartResponse {
    private List<String> labels;
    private List<Integer> values1;
    private List<Integer> values2;
    private List<Integer> values3;
}
