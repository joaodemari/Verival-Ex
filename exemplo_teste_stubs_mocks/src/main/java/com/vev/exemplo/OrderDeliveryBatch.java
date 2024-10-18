import java.util.ArrayList;

public class OrderDeliveryBatch {
    private IOrderDao dao;
    private DeliveryStartProcess delivery;

    public OrderDeliveryBatch(IOrderDao _dao, DeliveryStartProcess _delivery){
        this.dao = _dao;
        this.delivery = _delivery;
    }
    public void runBatch() {
    OrderDao dao = new OrderDao();
    DeliveryStartProcess delivery = new DeliveryStartProcess();
    List<Order> orders = dao.paidButNotDelivered();
    for (Order order : orders) {
    delivery.start(order);
    if (order.isInternational()) {
    order.setDeliveryDate("5 days from now");
    } else {
    order.setDeliveryDate("2 days from now");
    }
    }
    }
   }
interface IOrderDao{
    public void accessDB();
}

class FakeOrderDao implements IOrderDao{
    private ArrayList<string> Orders;

}

   class OrderDao implements IOrderDao {
    // acessa um banco de dados
   }
   class DeliveryStartProcess {
    // comunica-se com um serviço web
   }