package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.SectionReaderException;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.ZtmData;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;

/**
 * Created by lmonkiewicz on 17.03.2017.
 */
public class RootSectionReader extends AbstractSectionReader<ZtmData, Void> {

    private ZtmData ztmData = new ZtmData();

    @Override
    public ZtmData result() {
        return ztmData;
    }

    @Override
    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in) throws SectionReaderException {
        switch (sectionCode){
            case Sections.TY_DayTypes: {
                TYSectionReader reader = new TYSectionReader();
                reader.readSection(in);
                ztmData.setDayTypes(reader.result());
                break;
            }
            case Sections.KA_RoutesCalendar: {
                KASectionReader reader = new KASectionReader();
                reader.readSection(in);
                ztmData.setRoutesCalendar(reader.result());
                break;
            }
            case Sections.KD_LinesCalendar: {
                KDSectionReader reader = new KDSectionReader();
                reader.readSection(in);
                ztmData.setLinesCalendar(reader.result());
                break;
            }
            case Sections.ZA_BusStopGroupsList: {
                ZASectionReader reader = new ZASectionReader();
                reader.readSection(in);
                ztmData.setBusStopGroupsList(reader.result());
                break;
            }
            case Sections.ZP_BusStopGroups: {
                ZPSectionReader reader = new ZPSectionReader();
                reader.readSection(in);
                ztmData.setBusStopGroups(reader.result());
                break;
            }
            case Sections.SM_TownCodes: {
                SMSectionReader reader = new SMSectionReader();
                reader.readSection(in);
                ztmData.setTownCodes(reader.result());
                break;
            }
            case Sections.LL_Lines: {
                LLSectionReader reader = new LLSectionReader();
                reader.readSection(in);
                ztmData.setLines(reader.result());
                break;
            }
        }
    }

    @Override
    protected String getSectionCode() {
        return null;
    }
}
