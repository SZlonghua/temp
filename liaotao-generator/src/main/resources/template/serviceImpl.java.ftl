package ${package.ServiceImpl};

import com.example.commom.service.impl.BaseServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commom.model.PageUtil;
import com.example.commom.model.Query;
import org.apache.commons.lang.StringUtils;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends BaseServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    @Override
    public PageUtil<${entity}> list(Query query) {
        Page<${entity}> page = new Page<>(query.getPage(), query.getSize());

        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(query.getDirect())&&
            StringUtils.isNotEmpty(query.getOrder())){
            queryWrapper.orderBy(true,"ASC".equals(query.getDirect().toUpperCase()),query.getOrder());
        }else {
            queryWrapper.orderByDesc("modified_on");
        }

        IPage<${entity}> pageList = page(page, queryWrapper);
        return PageUtil.of(pageList.getRecords(),pageList.getTotal(),pageList.getSize(),pageList.getCurrent());
    }

    @Override
    public ${entity} info(String id) {
        return getById(id);
    }

    @Override
    public Boolean saveEntity(${entity} ${entity?uncap_first}) {
        return save(${entity?uncap_first});
    }

    @Override
    public Boolean updateEntity(${entity} ${entity?uncap_first}) {
        return saveOrUpdate(${entity?uncap_first});
    }

    @Override
    public Boolean delete(List<String> ids) {
        return removeByIds(ids);
    }
}
</#if>
