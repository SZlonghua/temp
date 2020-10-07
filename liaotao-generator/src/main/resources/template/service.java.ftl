package ${package.Service};

import ${package.Entity}.${entity};
import com.example.commom.service.BaseService;
import com.example.commom.model.PageUtil;
import com.example.commom.model.Query;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends BaseService<${entity}> {
    /**
    * 分页查询
    * @param query
    *          查询条件
    * @return  分页信息
    */
    PageUtil<${entity}> list(Query query);

    /**
    * 详情
    * @param id
    *          主键
    * @return  详情
    */
    ${entity} info(String id);
}
</#if>
