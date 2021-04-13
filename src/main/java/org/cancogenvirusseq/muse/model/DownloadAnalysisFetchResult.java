package org.cancogenvirusseq.muse.model;

import java.util.Optional;
import java.util.UUID;

import lombok.Getter;
import org.cancogenvirusseq.muse.model.song_score.Analysis;
import org.cancogenvirusseq.muse.model.song_score.AnalysisFile;
import org.cancogenvirusseq.muse.model.song_score.SongScoreClientException;
import org.springframework.http.HttpStatus;

public class DownloadAnalysisFetchResult {
  @Getter final UUID analysisId;
  final Analysis analysis;
  final SongScoreClientException exception;

  public DownloadAnalysisFetchResult(UUID analysisId, Analysis analysis) {
    this.analysisId = analysisId;
    this.analysis = analysis;
    this.exception = null;
  }

  public DownloadAnalysisFetchResult(UUID analysisId, SongScoreClientException exception) {
    this.analysisId = analysisId;
    this.analysis = null;
    this.exception = exception;
  }

  public String getResultMsg() {
    if (isExceptionStatus404Or400()) {
      return exception.getSongScoreErrorMsg();
    }
    if (analysis == null) {
      return "Analysis was not found";
    }
    if (!analysis.isPublished()) {
      return "Analysis is not published";
    }
    if (!analysis.hasFiles()) {
      return "Analysis doesn't have files";
    }
    return "Analysis is OK";
  }

  public Optional<SongScoreClientException> getException() {
    return Optional.ofNullable(exception);
  }

  public Optional<Analysis> getAnalysis() {
    return Optional.ofNullable(analysis);
  }

  public Optional<AnalysisFile> getFileInfo() {
    return Optional.ofNullable(analysis).map(a -> a.getFiles().get(0));
  }

  private Boolean isExceptionStatus404Or400() {
    return Optional.ofNullable(this.exception)
        .map(SongScoreClientException::getStatus)
        .map(status -> status.equals(HttpStatus.BAD_REQUEST) || status.equals(HttpStatus.NOT_FOUND))
        .orElse(false);
  }
}
