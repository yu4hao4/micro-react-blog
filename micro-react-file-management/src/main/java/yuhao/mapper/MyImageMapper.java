package yuhao.mapper;

import yuhao.annotation.Storage;
import yuhao.dto.req.FileReqDTO;
import yuhao.dto.resp.FileRespDTO;
import yuhao.entity.Image;

import java.util.List;

/**
 * @author yuhao5
 * @date 2020-08-05
 */
@Storage
public interface MyImageMapper {
    List<FileRespDTO> searchImageByFilterConditions(FileReqDTO fileReqDTO);

    int batchRemove(List<Image> images);
}
