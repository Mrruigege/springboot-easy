package ${package.ServiceImpl};


import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
<#--import ${superServiceImplClassPackage};-->
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbooteasy.framework.web.domain.AjaxResult;


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
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.entityPath}Mapper;

    @Override
    public int insert(${entity} ${table.entityPath}) {
    Integer id = ${table.entityPath}Mapper.insert(${table.entityPath});
    return id;
    }

    @Override
    public int deleteById(Integer id) {
    Integer row = ${table.entityPath}Mapper.deleteById(id);
    return row;
    }

    @Override
    public int update(${entity} ${table.entityPath}) {
    int row = ${table.entityPath}Mapper.update(${table.entityPath});
    return row;
    }


    @Override
    public ${entity} find${entity}ById(Integer id) {
    ${entity} ${table.entityPath} = ${table.entityPath}Mapper.find${entity}ById(id);
    return ${table.entityPath};
    }

}
</#if>