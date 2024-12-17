package br.com.carpag.app.dtos.generic;

public record FieldErrorDto(String name, String description) {

    @Override
    public String description() {
        return description;
    }

    @Override
    public String name() {
        return name;
    }
}
