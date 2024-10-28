package com.porto.dev_vagas.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateJobDTO(

        @Schema(example = "Desenvolvedor Java", requiredMode = Schema.RequiredMode.REQUIRED)
        String title,

        @Schema(example = "Desenvolver, testar e manter APIs e serviços backend utilizando linguagens como Python, Java, Node.js, etc;\n" +
                "Implementar e integrar banco de dados relacionais e não relacionais (ex: MySQL, PostgreSQL, MongoDB);\n" +
                "Ajudar na identificação e correção de bugs e problemas de performance;\n" +
                "Contribuir para a melhoria contínua de código, seguindo boas práticas e padrões de desenvolvimento;\n" +
                "Colaborar com equipes de frontend e DevOps para garantir a entrega de funcionalidades completas e estáveis;\n" +
                "Documentar processos e soluções técnicas.\n", requiredMode = Schema.RequiredMode.REQUIRED)
        String description,

        @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
        String level,

        @Schema(example = "Seguro de Vida: Pra cuidarmos do nosso bem maior: nossos PortoLovers.\n" +
                "\n" +
                "Participação nos Lucros\n" +
                "\n" +
                "Previdência Privada\n" +
                "\n" +
                "Bolsa de estudos\n" +
                "\n" +
                "Cursos de Idiomas\n" +
                "\n" +
                "Wellhub (Gympass)", requiredMode = Schema.RequiredMode.REQUIRED)
        String benefits
) {
}
