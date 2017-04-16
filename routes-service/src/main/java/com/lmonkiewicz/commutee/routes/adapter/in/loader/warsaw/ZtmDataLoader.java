package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.ZtmData;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section.RootSectionReader;
import lombok.extern.slf4j.Slf4j;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.util.ByteArrayStream;
import org.apache.commons.io.IOUtils;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * Created by lmonkiewicz on 16.04.2017.
 */
@Slf4j
public class ZtmDataLoader {

    @PostConstruct
    public void loadData(){
        log.info("Loading data");

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(fetchData(), "Windows-1250"))) {
            log.info("Parsing data");

            final RootSectionReader sectionReader = new RootSectionReader();
            sectionReader.readSection(reader);
            final ZtmData ztmData = sectionReader.result();
            log.info("Data parsed");

            processData(ztmData);

        } catch (IOException e) {
            log.error("Error while loading data",e);
        } catch (SectionReaderException e) {
            log.error("Error while parsing data",e);
        }
    }

    private void processData(ZtmData ztmData) {
        log.info("Loaded data: " + ztmData);
    }


    private InputStream fetchData() throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream("/ZTM_DATA_RA170422.7z");

        final byte[] bytes = IOUtils.toByteArray(inputStream);

        try (final ByteArrayStream bas = new ByteArrayStream(bytes, false)) {
            final IInArchive archive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, bas);

            try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                final ISimpleInArchive simpleInterface = archive.getSimpleInterface();
                if (simpleInterface.getNumberOfItems() > 0) {
                    simpleInterface.getArchiveItem(0).extractSlow(data -> {
                        try {
                            byteArrayOutputStream.write(data);
                            log.info("Loaded bytes:" + byteArrayOutputStream.size());
                        } catch (IOException e) {
                            log.error("Error while reading archive data", e);
                        }
                        return data.length;
                    });
                    final byte[] resultData = byteArrayOutputStream.toByteArray();
                    log.info("Decompressed data (B): " + resultData.length);
                    return new ByteArrayInputStream(resultData);
                }
                else {
                    log.warn("No files in the archive");
                    return new ByteArrayInputStream(new byte[0]);
                }
            }
        }
    }
}
