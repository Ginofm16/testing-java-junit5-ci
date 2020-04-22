package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Owner Map Service Text - ")
public class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp(){
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("First Before Each");
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero(){
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes{

        @BeforeEach
        void setUp(){
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);

            System.out.println("Nested Before each");
        }

        @Test
        void testPetCount(){
            int petTypeCount = petTypeService.findAll().size();

            assertThat(petTypeCount).isNotZero().isEqualTo(2);

            System.out.println("testPetCount Test");
        }

        @DisplayName("Save Owners Test - ")
        @Nested
        class SaveOwnersTest{

            @BeforeEach
            void setUp(){
                ownerMapService.save(new Owner(1L, "Before", "Each"));
                System.out.println("Saved Owner Before Each");
            }

            @Test
            void saveOwner(){
                Owner owner = new Owner(2L, "Joe", "Buck");
                Owner savedOwner = ownerMapService.save(owner);
                assertThat(savedOwner).isNotNull();
            }

            @DisplayName("Save Owner Test - ")
            @Nested
            class FindOwnersTest {
                @DisplayName("Find Owner")
                @Test
                void findOwner(){
                    System.out.println("findOwner");
                    Owner foundOwner = ownerMapService.findById(1L);

                    assertThat(foundOwner).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound(){
                    System.out.println("findOwnerNotFound");
                    Owner foundOwner = ownerMapService.findById(2L);

                    assertThat(foundOwner).isNull();
                }
            }

        }
    }

    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero(){
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }


}
