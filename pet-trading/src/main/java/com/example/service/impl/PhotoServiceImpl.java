package com.example.service.impl;

import com.example.dao.PhotoMapper;
import com.example.domain.Photo;
import com.example.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoMapper photoMapper;

    @Override
    public int addPhoto(Photo photo) {
        return photoMapper.addPhoto(photo);
    }

    @Override
    public int deletePhoto(Map<String, Object> map) {
        return photoMapper.deletePhoto(map);
    }

    @Override
    public int updatePhoto(Photo photo) {
        return photoMapper.updatePhoto(photo);
    }

    @Override
    public List<Photo> queryAllPhoto() {
        return photoMapper.queryAllPhoto();
    }

    @Override
    public List<Photo> queryPhotobypid(Long pid) {
        return photoMapper.queryPhotobypid(pid);
    }
}
