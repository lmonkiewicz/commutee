package com.lmonkiewicz.commutee.routes.parser.warsaw;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by lmonkiewicz on 2017-02-19.
 */
public abstract class AbstractSectionReader<T, B> implements SectionReader<T> {

    private B lastLineResult;

    @Override
    public void readSection(@NotNull BufferedReader in) throws SectionReaderException {
        try {
            String line = null;
            while ((line = in.readLine()) != null) {
                if (ZtmUtils.isSectionStart(line)) {
                    onSectionStart(ZtmUtils.extractSectionCode(line), in);
                } else if (ZtmUtils.isSectionEnd(line)) {
                    onSectionEnd(ZtmUtils.extractSectionCode(line), in);
                } else {
                    B lineResult = onSectionContentLine(line);
                    if (lineResult != null) {
                        setLastLineResult(lineResult);
                    }
                }
            }
        } catch (IOException e) {
            throw new SectionReaderException("There was an error while reading a section", e);
        }
    }

    protected void onSectionEnd(@NotNull String sectionCode, @NotNull BufferedReader in){}

    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in){};

    @Nullable
    protected B onSectionContentLine(@NotNull String line) { return null; };

    public void setLastLineResult(B lastLineResult) {
        this.lastLineResult = lastLineResult;
    }

    public B getLastLineResult() {
        return lastLineResult;
    }
}
