package ${package.Other};

<#list table.importPackages as pkg>
    <#if pkg?index_of("com.baomidou.mybatisplus") != 0>
import ${pkg};
    </#if>
</#list>

import com.fasterxml.jackson.annotation.JsonFormat;
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
     <#if field.propertyType?contains("Date") || field.propertyType?contains("Time")>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
<#------------  END 字段循环遍历  ---------->
}