/*package com.backend.almfit.config;

import com.backend.almfit.dto.alimento.AlimentoDTO;
import com.backend.almfit.dto.alimento.AlimentoTypeDTO;
import com.backend.almfit.entity.User.Role;
import com.backend.almfit.entity.User.RoleType;
import com.backend.almfit.entity.User.Usuario;
import com.backend.almfit.repository.RoleRepository;
import com.backend.almfit.repository.UserRepository;
import com.backend.almfit.service.AlimentoService;
import com.backend.almfit.service.AlimentoTypeService;
import com.backend.almfit.service.UsuarioService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class InitData {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UsuarioService usuarioService;
    private final AlimentoService alimentoService;
    private final AlimentoTypeService alimentoTypeService;

    @PostConstruct
    private void initData() {

        if (alimentoTypeService.findAll().size() < 5) {
            AlimentoTypeDTO alimentoTypeDto1 = AlimentoTypeDTO.builder()
                    .nombre("proteinas")
                    .build();

            AlimentoTypeDTO alimentoTypeDto2 = AlimentoTypeDTO.builder()
                    .nombre("carbohidratos")
                    .build();

            AlimentoTypeDTO alimentoTypeDto3 = AlimentoTypeDTO.builder()
                    .nombre("grasas")
                    .build();

            AlimentoTypeDTO alimentoTypeDto4 = AlimentoTypeDTO.builder()
                    .nombre("carnes")
                    .build();

            AlimentoTypeDTO alimentoTypeDto5 = AlimentoTypeDTO.builder()
                    .nombre("vegetales")
                    .build();

            AlimentoTypeDTO alimentoTypeDto6 = AlimentoTypeDTO.builder()
                    .nombre("frutas")
                    .build();

            AlimentoTypeDTO alimentoTypeDto7 = AlimentoTypeDTO.builder()
                    .nombre("lacteos")
                    .build();

            AlimentoTypeDTO alimentoTypeDto8 = AlimentoTypeDTO.builder()
                    .nombre("cereales")
                    .build();

            AlimentoTypeDTO alimentoTypeDto9 = AlimentoTypeDTO.builder()
                    .nombre("pescados")
                    .build();

            AlimentoTypeDTO alimentoTypeDto10 = AlimentoTypeDTO.builder()
                    .nombre("frutos secos")
                    .build();

            alimentoTypeService.save(alimentoTypeDto1);
            alimentoTypeService.save(alimentoTypeDto2);
            alimentoTypeService.save(alimentoTypeDto3);
            alimentoTypeService.save(alimentoTypeDto4);
            alimentoTypeService.save(alimentoTypeDto5);
            alimentoTypeService.save(alimentoTypeDto6);
            alimentoTypeService.save(alimentoTypeDto7);
            alimentoTypeService.save(alimentoTypeDto8);
            alimentoTypeService.save(alimentoTypeDto9);
            alimentoTypeService.save(alimentoTypeDto10);

        }



        if (alimentoService.findAll().size() < 7) {

            AlimentoDTO alimentoDto1 = AlimentoDTO.builder()
                    .nombre("Arroz")
                    .proteinas(2.5)
                    .carbohidratos(25.0)
                    .grasas(0.5)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("cereales"), alimentoTypeService.findByNombreDTO("carbohidratos")))
                    .build();

            AlimentoDTO alimentoDto2 = AlimentoDTO.builder()
                    .nombre("Pollo")
                    .proteinas(20.0)
                    .carbohidratos(0.0)
                    .grasas(5.0)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("carnes"), alimentoTypeService.findByNombreDTO("proteinas")))
                    .build();

            AlimentoDTO alimentoDto3 = AlimentoDTO.builder()
                    .nombre("Leche")
                    .proteinas(3.0)
                    .carbohidratos(5.0)
                    .grasas(3.0)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("lacteos")))
                    .build();

            AlimentoDTO alimentoDto4 = AlimentoDTO.builder()
                    .nombre("Manzana")
                    .proteinas(0.5)
                    .carbohidratos(15.0)
                    .grasas(0.5)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("frutas")))
                    .build();

            AlimentoDTO alimentoDto5 = AlimentoDTO.builder()
                    .nombre("Pescado")
                    .proteinas(20.0)
                    .carbohidratos(0.0)
                    .grasas(5.0)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("pescados"), alimentoTypeService.findByNombreDTO("proteinas")))
                    .build();

            AlimentoDTO alimentoDto6 = AlimentoDTO.builder()
                    .nombre("Nuez")
                    .proteinas(5.0)
                    .carbohidratos(10.0)
                    .grasas(20.0)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("frutos secos")))
                    .build();

            AlimentoDTO alimentoDto7 = AlimentoDTO.builder()
                    .nombre("Zanahoria")
                    .proteinas(1.0)
                    .carbohidratos(10.0)
                    .grasas(0.5)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("vegetales")))
                    .build();

            AlimentoDTO alimentoDto8 = AlimentoDTO.builder()
                    .nombre("Queso")
                    .proteinas(10.0)
                    .carbohidratos(5.0)
                    .grasas(10.0)
                    .caloriasPorcion(100.0)
                    .tipos(Arrays.asList(alimentoTypeService.findByNombreDTO("lacteos")))
                    .build();


            alimentoService.saveAll(Arrays.asList(alimentoDto1, alimentoDto2, alimentoDto3, alimentoDto4, alimentoDto5, alimentoDto6, alimentoDto7, alimentoDto8));
        }





        if (userRepository.findAll().size() < 4) {
            // Inicializar roles
            Role userRole = Role.builder().name(RoleType.ROLE_USER).build();
            Role adminRole = Role.builder().name(RoleType.ROLE_ADMIN).build();
            roleRepository.save(userRole);
            roleRepository.save(adminRole);

            // Inicializar usuarios
            Usuario user1 = Usuario.builder()
                    .username("user")
                    .email("user@example.com")
                    .firstName("Usuario")
                    .lastName("Regular")
                    .password("123")
                    .roles(new HashSet<>(Arrays.asList(userRole)))
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .build();

            Usuario admin1 = Usuario.builder()
                    .username("admin")
                    .email("admin@example.com")
                    .firstName("Administrador")
                    .lastName("Admin")
                    .password("123")
                    .roles(new HashSet<>(Arrays.asList(adminRole, userRole)))
                    .birthDate(LocalDate.of(2001, 2, 8))
                    .build();

            Usuario user2 = Usuario.builder()
                    .username("user2")
                    .email("user2@gmail.com")
                    .firstName("Usuario2")
                    .lastName("Regular2")
                    .password("123")
                    .birthDate(LocalDate.of(1995, 5, 5))
                    .roles(new HashSet<>(Arrays.asList(userRole)))
                    .build();

            Usuario admin2 = Usuario.builder()
                    .username("admin2")
                    .email("admin2@gmail.com")
                    .firstName("Administrador2")
                    .lastName("Admin2")
                    .password("123")
                    .birthDate(LocalDate.of(2000, 10, 10))
                    .roles(new HashSet<>(Arrays.asList(adminRole)))
                    .build();


            usuarioService.createUsuarios(Arrays.asList(user1, admin1, user2, admin2));


        }
    }
}


 */

