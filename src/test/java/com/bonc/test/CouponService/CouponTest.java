package com.bonc.test.CouponService;

import com.bonc.domain.User;
import org.junit.Test;
import org.springframework.boot.autoconfigure.session.SessionProperties;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.WeakHashMap;

/**
 * description: Java引用类型 强、弱、软、虚引用
 * 软引用（SoftReference）的含义是，
 * 如果一个对象只具有软引用，而当前虚拟机堆内存空间足够，那么垃圾回收器就不会回收它，反之就会回收这些软引用指向的对象。
 *
 * 使用场景: 比如在一个博客管理系统里，为了提升访问性能，在用户在点击博文时，如果这篇博文没有缓存到内存中，则需要做缓存动作，
 * 这样其它用户在点击同样这篇文章时，就能直接从内存里装载，而不用走数据库，这样能降低响应时间。
 *
 * 弱引用（WeakReference）与软引用的区别在于，
 * 垃圾回收器一旦发现某块内存上只有弱引用（一定请注意只有弱引用，没强引用），不管当前内存空间是否足够，那么都会回收这块内存。
 *
 * 使用场景: 弱引用实现优惠券
 * <br />
 * Created by mace on 11:39 2018/5/18.
 */
public class CouponTest {

    @Test
    public void test(){

        WeakHashMap<Coupon,List<WeakReference<User>>> couponListWeakHashMap = new WeakHashMap<>();

    }
}
