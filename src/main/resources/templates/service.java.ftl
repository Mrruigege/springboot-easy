package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.example.springbooteasy.framework.web.domain.AjaxResult;

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
public interface ${table.serviceName} {

    int  insert(${entity} ${table.entityPath});
    int  deleteById(Integer id);
    int  update(${entity} ${table.entityPath});
    ${entity}  find${entity}ById(Integer id);

}
</#if>