package com.example.generator.util;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liaotao
 * @Date: 2018/11/20 10:43
 * @Description:
 */
public class CodeGeneratorUtil {
    //设置作者
    private final static String author = "liaotao";
    //设置包名
    private final static String packageName = "com.example.generator";
    //设置模块名
    private final static String moduleName = "job";
    //设置数据库要生成代码的表名
    private final static String tableName = "scheduler_job";
    //文件覆盖:true 覆盖原有文件 false 不覆盖原有文件
    private final static boolean fileOverride = true;
    //设置数据库url
    private final static String url = "jdbc:mysql://192.168.2.36:3306/liaotao-eaxm?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    //设置数据库驱动
    //private final static String driverName = "org.mariadb.jdbc.Driver";
    private final static String driverName = "com.mysql.jdbc.Driver";
    //设置数据库用户名
    private final static String username = "root";
    //设置数据库密码
    private final static String password = "root";
    //设置数据库密码
    private final static String projectPath = System.getProperty("user.dir")+"/liaotao-generator";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setServiceName("%sService");//设置生成的service接口的名字的首字母是否为I
        gc.setFileOverride(fileOverride);  // 文件覆盖
        gc.setDateType(DateType.ONLY_DATE);//时间类型转换策略java.ExexuteQualityRecordUtil.Date
        gc.setSwagger2(true);   //开启swagger
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);

        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);   //设置模块名
        pc.setParent(packageName);  //设置包名
        pc.setMapper("mapper"); //设置dao包名
        pc.setServiceImpl("service.impl");

        mpg.setPackageInfo(pc);


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("aut", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 自定义 Entity.java 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/template/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath+"/src/main/java/"+pc.getParent().replace(".","/")+ "/entity/" + tableInfo.getEntityName() + ".java";
            }
        });
        focList.add(new FileOutConfig("/template/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath+"/src/main/resources/"+ "/mapping/" + moduleName+"/"+ tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        // 自定义 repository.java 生成
        focList.add(new FileOutConfig("/template/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath+"/src/main/java/"+pc.getParent().replace(".","/")+ "/mapper/" + tableInfo.getEntityName() + "Mapper.java";
            }
        });

        // 自定义 service.java 生成
        focList.add(new FileOutConfig("/template/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath+"/src/main/java/"+pc.getParent().replace(".","/")+ "/service/" + tableInfo.getEntityName() + "Service.java";
            }
        });


        // 自定义 serviceImpl.java 生成
        focList.add(new FileOutConfig("/template/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath+"/src/main/java/"+pc.getParent().replace(".","/")+ "/service/impl/" + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });

        // 自定义 controller.java 生成
        focList.add(new FileOutConfig("/template/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath+"/src/main/java/"+pc.getParent().replace(".","/")+ "/controller/" + tableInfo.getEntityName() + "Controller.java";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //取消全部默认生成，改用自定义模板
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setServiceImpl(null);
        tc.setService(null);
        tc.setMapper(null);
        tc.setEntity(null);
        tc.setXml(null);


        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);



        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableName);





        //strategy.setSuperMapperClass("com.example.commom.mapper.BaseMapper");
        //strategy.setSuperEntityColumns("createOn","createBy","modifiedOn","modifiedBy");
        //strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
