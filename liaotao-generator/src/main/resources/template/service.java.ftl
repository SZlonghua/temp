package ${package.Service};

import ${package.Entity}.${entity};
import com.example.commom.service.BaseService;
import com.example.commom.model.PageUtil;
import com.example.commom.model.Query;
import java.util.List;
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

    /**
    * 保存
    * @param ${entity?uncap_first}
    *          实体
    * @return  是否成功
    */
    Boolean saveEntity(${entity} ${entity?uncap_first});

    /**
    * 更新
    * @param ${entity?uncap_first}
    *          实体
    * @return  是否成功
    */
    Boolean updateEntity(${entity} ${entity?uncap_first});

    /**
    * 删除
    * @param ids
    *          主键
    * @return  是否成功
    */
    Boolean delete(List<String> ids);
}
</#if>
