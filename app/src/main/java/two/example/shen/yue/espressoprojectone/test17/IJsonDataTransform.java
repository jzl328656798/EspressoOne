package two.example.shen.yue.espressoprojectone.test17;

public interface IJsonDataTransform<T> {

    void onSuccess(T t);

    void onFailure();

}
