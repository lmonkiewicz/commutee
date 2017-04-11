package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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
                    final String sectionCode = ZtmUtils.extractSectionCode(line);

                    onSectionEnd(sectionCode, in);

                    if (Objects.equals(getSectionCode(), sectionCode)){
                        break;
                    }

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

    protected abstract String getSectionCode();

    protected void onSectionEnd(@NotNull String sectionCode, @NotNull BufferedReader in){}

    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in) throws SectionReaderException {};

    @Nullable
    protected B onSectionContentLine(@NotNull String line) { return null; };

    public void setLastLineResult(B lastLineResult) {
        this.lastLineResult = lastLineResult;
    }

    public Optional<B> getLastLineResult() {
        return Optional.ofNullable(lastLineResult);
    }
}
