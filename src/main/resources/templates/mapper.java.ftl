package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;

/**
* <p>
* ${table.comment!} Mapper 接口
* </p>
*
* @author ${author}
* @since ${date}
*/
@Mapper
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} {

    int  insert(${entity} ${table.entityPath});
    int  deleteById(Integer id);
    int  update(${entity} ${table.entityPath});
    ${entity} find${entity}ById(Integer id);
}
</#if>