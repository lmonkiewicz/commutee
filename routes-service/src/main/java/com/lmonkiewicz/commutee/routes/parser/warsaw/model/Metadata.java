package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
@Data
public class Metadata {
    private LocalDate validFrom;
    private List<String> header = Lists.newArrayList();
    private List<String> comments = Lists.newArrayList();
    private List<String> symbols = Lists.newArrayList();

}
