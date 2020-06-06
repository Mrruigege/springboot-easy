package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.springbooteasy.framework.web.domain.AjaxResult;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>


/**
* <p>
* ${table.comment!} 内部接口
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
@RequestMapping("/${table.entityPath}")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Autowired
    public ${table.serviceName} ${table.entityPath}Service;

    /**
    * 新增
    *
    * @param ${table.entityPath}
    * @return
    */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ${entity} ${table.entityPath}){
    int id = ${table.entityPath}Service.insert(${table.entityPath});
    if(id > 0){
        return AjaxResult.success();
    }else{
        return AjaxResult.error();
    }
    }

    /**
    * 修改
    *
    * @param ${table.entityPath}
    * @return
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody ${entity} ${table.entityPath}) {
    int row = ${table.entityPath}Service.update(${table.entityPath});
    if (row > 0) {
        return AjaxResult.success();
    } else {
        return AjaxResult.error();
    }
    }

    /**
    * 删除
    *
    * @param id 主键id
    * @return
    */
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable int id) {
    int row = ${table.entityPath}Service.deleteById(id);
    if (row > 0) {
        return AjaxResult.success();
    } else {
        return AjaxResult.error();
    }
    }

    /**
    * 查询详情
    *
    * @param id 主键id
    * @return
    */
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public AjaxResult findOne(@PathVariable int id) {
    ${entity} ${table.entityPath} = ${table.entityPath}Service.find${entity}ById(id);
    if (${table.entityPath} == null) {
        return AjaxResult.error();
        }
    return AjaxResult.success(${table.entityPath});
    }

}
</#if>