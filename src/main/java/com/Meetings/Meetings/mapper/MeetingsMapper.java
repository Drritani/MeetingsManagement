package com.Meetings.Meetings.mapper;

import com.Meetings.Meetings.entity.Meetings;
import com.Meetings.Meetings.dto.MeetingsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingsMapper extends BaseMapper<Meetings,MeetingsDTO>{
    MeetingsMapper MEETING_MAPPER= Mappers.getMapper(MeetingsMapper.class);

}
