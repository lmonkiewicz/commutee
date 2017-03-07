package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Route;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Routes;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class TRSectionReader extends AbstractSectionReader<Routes, Route>{

    private Routes routes = new Routes();

    @Override
    public Routes result() {
        return routes;
    }


    @Override
    protected String getSectionCode() {
        return "TR";
    }
}
