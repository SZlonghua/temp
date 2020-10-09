package com.example.commom.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.commom.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        Object originalObject = metaObject.getOriginalObject();
        if(originalObject instanceof BaseEntity){
            this.strictInsertFill(metaObject, "createOn", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
            this.strictInsertFill(metaObject, "createBy", String.class, "admin"); // 起始版本 3.3.0(推荐使用)
            this.strictInsertFill(metaObject, "modifiedOn", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
            this.strictInsertFill(metaObject, "modifiedBy", String.class, "admin"); // 起始版本 3.3.0(推荐使用)
        }
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        Object originalObject = metaObject.getOriginalObject();
        if(originalObject instanceof BaseEntity){
            this.strictUpdateFill(metaObject, "modifiedOn", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
            this.strictUpdateFill(metaObject, "modifiedBy", String.class, "admin"); // 起始版本 3.3.0(推荐使用)
        }

    }
}
