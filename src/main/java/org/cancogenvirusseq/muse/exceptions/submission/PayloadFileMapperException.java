package org.cancogenvirusseq.muse.exceptions.submission;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.cancogenvirusseq.muse.exceptions.MuseBaseException;
import org.springframework.http.HttpStatus;

@Value
@EqualsAndHashCode(callSuper = true)
public class PayloadFileMapperException extends Throwable implements MuseBaseException {
  List<String> sampleIdInFileMissingInTsv;
  List<String> sampleIdInRecordMissingInFile;

  @Override
  public String getErrorMessage() {
    return "Found records that are missing samples and/or samples that are missisng records";
  }

  public Map<String, Object> getErrorInfo() {
    return Map.of(
        "sampleIdInFileMissingInTsv", sampleIdInFileMissingInTsv,
        "sampleIdInRecordMissingInFile", sampleIdInRecordMissingInFile);
  }
}
