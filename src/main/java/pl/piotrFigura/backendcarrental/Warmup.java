//package pl.piotrFigura.backendcarrental;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import pl.piotrFigura.backendcarrental.model.CarEntity;
//import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
//import pl.piotrFigura.backendcarrental.model.CityEntity;
//import pl.piotrFigura.backendcarrental.repository.CarMarkRepository;
//import pl.piotrFigura.backendcarrental.repository.CityNameRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Slf4j
//@Component
//class Warmup implements ApplicationListener<ContextRefreshedEvent> {
//    private final CarMarkRepository carMarkRepository;
//    private final CityNameRepository cityNameRepository;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        log.info("Start car table");
//        if (carMarkRepository.findAll().isEmpty()) {
//            List<CarMarkEntity> carMarkEntityList = new ArrayList<>(List.of(
//                    new CarMarkEntity("MAZDA"),
//                    new CarMarkEntity("BMW"))
//            );
//            carMarkEntityList.forEach(entity -> carMarkRepository.save(entity));
//            log.info("Starting marks added");
//        }
//        if (cityNameRepository.findAll().isEmpty()) {
//            List<CityEntity> cityEntities = new ArrayList<>(List.of(
//                    new CityEntity("WARSZAWA"),
//                    new CityEntity("KRAKOW"))
//            );
//            cityEntities.forEach(entity -> cityNameRepository.save(entity));
//            log.info("Starting cities added");
//        }
//    }
//}
