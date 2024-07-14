package academy.kata.mis.kafkatest.model.enums;

public enum DocumentDestination {
    PERSONAL,//доступ только владельца
    SYSTEM_MEDICAL,//доступ только для врачей одной системы
    SYSTEM,//доступ только для участников одной системы
    GLOBAL//публичный доступ

}
