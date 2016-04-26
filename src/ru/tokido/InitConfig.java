package ru.tokido;

/**
 * Created by oparin_ag on 20.04.2016.
 */
public class InitConfig {
    //проверка конфиг файлов, вернет false если конфигов нету
    //upd1 в будущем замутить проверку синтаксиса конфигов
    private volatile boolean status = false;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
