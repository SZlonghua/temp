package ${package.Controller};


<#if restControllerStyle>
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.Service}.${table.serviceName};
import com.example.commom.model.R;
import com.example.commom.model.PageUtil;
import ${package.Entity}.${entity};
import com.example.commom.model.Query;
import com.example.commom.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.example.commom.valid.AddGroup;
import com.example.commom.valid.UpdateGroup;
import javax.validation.constraints.NotEmpty;

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
@Validated
public class ${table.controllerName} extends BaseController {
</#if>
    @Autowired
    ${table.serviceName} ${table.serviceName?uncap_first};

    @GetMapping("list")
    @ApiOperation(value = "列表")
    public R<PageUtil<${entity}>> list(@Validated Query query) {
        return R.ok(${table.serviceName?uncap_first}.list(query));
    }

    @GetMapping("info/{id}")
    @ApiOperation(value = "详情")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id",value = "主键",required = true,dataType = "String")
    })
    public R<${entity}> info(@NotEmpty(message = "主键不能为空") @PathVariable(value = "id") String id) {
        return R.ok(${table.serviceName?uncap_first}.info(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存")
    public R<Boolean> save(@Validated({AddGroup.class}) @RequestBody ${entity} ${entity?uncap_first}){
        return R.ok(${table.serviceName?uncap_first}.saveEntity(${entity?uncap_first}));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新")
    public R<Boolean> update(@Validated({UpdateGroup.class}) @RequestBody ${entity} ${entity?uncap_first}){
        return R.ok(${table.serviceName?uncap_first}.updateEntity(${entity?uncap_first}));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ids",value = "主键",required = true,dataType = "String",allowMultiple = true)
    })
    public R<Boolean> delete(@NotEmpty(message = "主键不能为空") @RequestBody List<String> ids){
        return R.ok(schedulerJobService.delete(ids));
    }
}
</#if>
