package com.dantesoft.siremono.internal.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractOutput<T> implements CommandOutput {

	@Schema(description = "The returned payload")
	protected T payload;

	protected AbstractOutput() {
	}

	protected AbstractOutput(T payload) {
		this.payload = payload;
	}

	public static <T, O extends AbstractOutput<T>> O of(Class<O> clazz, T data) {
		try {
			O inst = clazz.getDeclaredConstructor().newInstance(); 
			inst.setPayload(data); 
			return inst;
		} catch (Exception e) {
			throw new IllegalStateException("No se pudo instanciar " + clazz, e);
		}
	}

}
