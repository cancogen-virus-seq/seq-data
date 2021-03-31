package org.cancogenvirusseq.muse.components.songscore.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScoreFileSpec {
  private String objectId;
  private String uploadId;
  private List<Part> parts;

  @Data
  @NoArgsConstructor
  public static class Part {
    Integer partNumber;
    Long partSize;
    Long offset;
    String url;
  }
}
