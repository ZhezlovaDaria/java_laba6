package shopd.controller;

import shopd.model.Buyer;
import shopd.model.Goods;
import shopd.model.MyException;
import shopd.model.Order;
import shopd.services.GoodsService;
import shopd.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {

    GoodsService goodsService;
    OrdersService ordersService;
    Buyer buyer;
    List<Goods> basketGoods = new ArrayList<Goods>();
    Map<Goods, Integer> basketMap = new LinkedHashMap<>();

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        modelAndView.addObject("buyer", buyer);
        return modelAndView;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ModelAndView allGoods() {
        List<Goods> goods = goodsService.allGoods();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods");
        modelAndView.addObject("goodsList", goods);
        modelAndView.addObject("buyer", buyer);
        return modelAndView;
    }

    @RequestMapping(value = "/goods/addGoods", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addGoods");
        return modelAndView;
    }

    @RequestMapping(value = "/goods/addGoods", method = RequestMethod.POST)
    public ModelAndView addGoods(@ModelAttribute("goods") Goods goods) throws MyException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        goodsService.save(goods);
        return modelAndView;
    }

    @RequestMapping(value="/goods/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGoods(@PathVariable("id") int id) throws MyException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        Goods goods = goodsService.find(id);
        goodsService.delete(goods);
        return modelAndView;
    }

    @RequestMapping(value = "/addProfile", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addProfile");
        buyer = new Buyer();
        modelAndView.addObject("buyer", buyer);
        return modelAndView;
    }

    @RequestMapping(value = "/addProfile", method = RequestMethod.POST)
    public ModelAndView addProfile(@ModelAttribute("newBuyer") Buyer newBuyer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile");
        buyer = newBuyer;
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView buyerProfile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("buyer", buyer);
        return modelAndView;
    }

    @RequestMapping(value="/goods/addBasket/{id}", method = RequestMethod.GET)
    public ModelAndView addBasket(@PathVariable("id") int id) throws MyException {
        Goods goods = goodsService.find(id);

        if (!goodsService.check(goods, basketMap))
            basketMap.put(goods, 1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods");
        return modelAndView;
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView basket() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basket");
        modelAndView.addObject("buyer", buyer);
        modelAndView.addObject("basketMap", basketMap);
        return modelAndView;
    }

    @RequestMapping(value="/basket/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBasket(@PathVariable("id") int id) throws MyException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/basket");
        Goods goods = goodsService.find(id);
        basketGoods = goodsService.basketDelete(goods, basketGoods);
        return modelAndView;
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public ModelAndView refresh(@RequestParam List<Integer> quantity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basket");
        basketMap = goodsService.restatement(basketMap, quantity);
        modelAndView.addObject("buyer", buyer);
        modelAndView.addObject("basketMap", basketMap);
        return modelAndView;
    }

    @RequestMapping(value = "/order/confirm", method = RequestMethod.GET)
    public ModelAndView confirmOrder() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderConfirm");

        modelAndView.addObject("buyer", buyer);
        modelAndView.addObject("basketMap", basketMap);
        return modelAndView;
    }

    @RequestMapping(value = "/order/confirm", method = RequestMethod.POST)
    public ModelAndView placeOrder(
            @RequestParam List<String> goods
            , @RequestParam String email
            , @RequestParam String phone
            , @RequestParam String address
            , @RequestParam String name
            , @RequestParam List<Integer> value
            , @RequestParam Double total) throws MyException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/orders");
        String names = ordersService.goodsNames(goods);
        String quantity = ordersService.goodsQuantity(value);
        Order order = new Order();
        order.setName(name);
        order.setEmail(email);
        order.setPhone(phone);
        order.setAddress(address);
        order.setGoods(names);
        order.setQuantity(quantity);
        order.setSum(total);
        ordersService.save(order);
        return modelAndView;
    }
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView orders() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orders");
        List<Order> orders = ordersService.allOrders();
        modelAndView.addObject("buyer", buyer);
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }
}
