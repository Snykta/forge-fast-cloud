package ${package.Other};

<#list table.importPackages as pkg>
    <#if pkg?index_of("com.baomidou.mybatisplus") != 0>
import ${pkg};
    </#if>
</#list>

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* <p>
*  ${entity}Dto
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
@ApiModel(value = "${entity} 对应Dto实体类")
public class ${entity}Dto implements Serializable {

private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    /**
    * ${field.comment}
    */
    @ApiModelProperty("${field.comment}")
    private ${field.propertyType} ${field.propertyName};

</#list>
<#------------  END 字段循环遍历  ---------->
}