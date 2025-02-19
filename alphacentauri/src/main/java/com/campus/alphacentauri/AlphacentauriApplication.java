package com.campus.alphacentauri;

import com.campus.alphacentauri.TipoInteraccion.application.TipoInteraccionServImp;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccionDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class 	AlphacentauriApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AlphacentauriApplication.class, args);

		TipoInteraccionServImp typeInterationService = context.getBean(TipoInteraccionServImp.class);

		TipoInteraccionDTO typeInterationDTO1 = new TipoInteraccionDTO();
		typeInterationDTO1.setType("Like");
		TipoInteraccionDTO typeInterationDTO2 = new TipoInteraccionDTO();
typeInterationDTO2.setType("Comment");

		typeInterationService.save(typeInterationDTO1);
		typeInterationService.save(typeInterationDTO2);
	}

}
