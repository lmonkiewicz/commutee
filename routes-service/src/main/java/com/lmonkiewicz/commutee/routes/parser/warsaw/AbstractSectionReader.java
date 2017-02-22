package com.lmonkiewicz.commutee.routes.parser.warsaw;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by lmonkiewicz on 2017-02-19.
 */
public abstract class AbstractSectionReader<T> implements SectionReader<T> {

    @Override
    public void readSection(@NotNull BufferedReader in) throws SectionReaderException {
        try {
            String line = null;
            while((line = in.readLine()) != null){
                if (ZtmUtils.isSectionStart(line)) {
                    onSectionStart(ZtmUtils.extractSectionCode(line), in);
                }
                else if (ZtmUtils.isSectionEnd(line)){
                    onSectionEnd(ZtmUtils.extractSectionCode(line), in);
                }
                else {
                    onSectionContentLine(line);
                }
            }
        } catch (IOException e) {
            throw new SectionReaderException("There was an error while reading a section", e);
        }
    }

    protected void onSectionEnd(@NotNull String sectionCode, @NotNull BufferedReader in){}

    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in){};

    protected void onSectionContentLine(@NotNull String line) {};
}
