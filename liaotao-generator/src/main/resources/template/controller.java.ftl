package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import com.example.commom.model.R;
import com.example.commom.model.PageUtil;
import ${package.Entity}.${entity};
import com.example.commom.model.Query;
import com.example.commom.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Api(tags = {"${table.comment!}"})
public class ${table.controllerName} extends BaseController {
</#if>
    @Autowired
    ${table.serviceName} ${table.serviceName?uncap_first};

    @GetMapping("list")
    @ApiOperation(value = "列表")
    public R<PageUtil<SchedulerJob>> list(Query query) {
        return R.ok(${table.serviceName?uncap_first}.list(query));
    }
}
</#if>
