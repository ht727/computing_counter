package com.huangliutan.service.impl;

import com.huangliutan.entity.Records;
import com.huangliutan.entity.RequestParam;
import com.huangliutan.service.ComputeService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComputeServiceImpl implements ComputeService {
    @Override
    public List<Records> compute(RequestParam requestParam) {

        //借款金额：
        double borrow = Double.valueOf(requestParam.getBorrow());
        //期数
        int period = Integer.valueOf(requestParam.getPeriod());
        //利率
        double interestRate = Double.valueOf(requestParam.getInterestRate());
        //费用
        double cost = Double.valueOf(requestParam.getCost());

        List<Records> list = new ArrayList<>();
        Records records0 = new Records();
        records0.setPeriodNumber("期数");
        records0.setRepaymentDate("还款日期");
        records0.setMonthlyPrincipal("每月还款本金");
        records0.setMonthlyInterest("每月还款利息");
        if ("1".equals(requestParam.getLoan())) {
            records0.setMonthlyRent("停车费");
        } else {
            records0.setMonthlyRent("GBS月租");
        }

        records0.setTotalA("合计");
        list.add(records0);

        //先息后本,整交
        if ("1".equals(requestParam.getPay()) && "1".equals(requestParam.getRent())) {
            double monthlyInterest = (borrow * interestRate) / (period * 100);
            //保留两位数
            double monthlyInterest2 = (double) Math.round(monthlyInterest * 100) / 100;
            for (int i = 0; i <= period; i++) {
                if (i == 0) {
                    Records records = new Records();
                    records.setId(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    records.setPeriodNumber("首月扣除");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal("0.00");
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent(String.valueOf(cost));
                    records.setTotalA(String.valueOf((double) Math.round((monthlyInterest2 + cost) * 100) / 100));//保留两位
                    list.add(records);
                } else if (i == period) {
                    Records records = new Records();
                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal(String.valueOf(cost));
                    records.setMonthlyInterest("0.00");
                    records.setMonthlyRent("0.00");
                    records.setTotalA(String.valueOf(cost));
                    list.add(records);
                } else {
                    Records records = new Records();

                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal("0.00");
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent("0.00");
                    records.setTotalA(String.valueOf(monthlyInterest2));
                    list.add(records);
                }
            }
        }

        //先息后本，每月
        if ("1".equals(requestParam.getPay()) && "2".equals(requestParam.getRent())) {
            double monthlyInterest = (borrow * interestRate) / (period * 100);
            double monthlyInterest2 = (double) Math.round(monthlyInterest * 100) / 100;
            for (int i = 0; i <= period; i++) {
                if (i == 0) {
                    Records records = new Records();
                    records.setId(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    records.setPeriodNumber("首月扣除");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal("0.00");
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent(String.valueOf(cost));
                    records.setTotalA(String.valueOf((double) Math.round((monthlyInterest2 + cost) * 100) / 100));
                    list.add(records);
                } else if (i == period) {
                    Records records = new Records();
                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal(String.valueOf(borrow));
                    records.setMonthlyInterest("0.00");
                    records.setMonthlyRent("0.00");
                    records.setTotalA(String.valueOf(borrow));
                    list.add(records);
                } else {
                    Records records = new Records();
                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal("0.00");
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent(String.valueOf(cost));
                    records.setTotalA(String.valueOf((double) Math.round((monthlyInterest2 + cost) * 100) / 100));
                    list.add(records);
                }
            }
        }

        //等额等息
        //整月
        if ("2".equals(requestParam.getPay()) && "1".equals(requestParam.getRent())) {
            //等息
            double monthlyInterest = (borrow * interestRate) / (period * 100);
            double monthlyInterest2 = (double) Math.round(monthlyInterest * 100) / 100;
            //等额
            double equalInterest = (borrow) / (period);
            double equalInterest2 = (double) Math.round(equalInterest * 100) / 100;
            for (int i = 0; i <= period; i++) {
                if (i == 0) {
                    Records records = new Records();
                    records.setId(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    records.setPeriodNumber("首月扣除");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal("0.00");
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent(String.valueOf(cost));
                    records.setTotalA(String.valueOf((double) Math.round((monthlyInterest2 + cost) * 100) / 100));
                    list.add(records);
                } else if (i == period) {
                    Records records = new Records();
                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal(String.valueOf(equalInterest2));
                    records.setMonthlyInterest("0.00");
                    records.setMonthlyRent("0.00");
                    records.setTotalA(String.valueOf(equalInterest2));
                    list.add(records);
                } else {
                    Records records = new Records();

                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal(String.valueOf(equalInterest2));
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent("0.00");
                    records.setTotalA(String.valueOf((double) Math.round((monthlyInterest2 + equalInterest2) * 100) / 100));
                    list.add(records);
                }
            }
        }

        //每月
        if ("2".equals(requestParam.getPay()) && "2".equals(requestParam.getRent())) {
            double monthlyInterest = (borrow * interestRate) / (period * 100);
            double monthlyInterest2 = (double) Math.round(monthlyInterest * 100) / 100;
            double equalInterest = (borrow) / (period);
            double equalInterest2 = (double) Math.round(equalInterest * 100) / 100;
            for (int i = 0; i <= period; i++) {
                if (i == 0) {
                    Records records = new Records();
                    records.setId(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    records.setPeriodNumber("首月扣除");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal("0.00");
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent(String.valueOf(cost));
                    records.setTotalA(String.valueOf((double) Math.round((monthlyInterest2 + cost) * 100) / 100));
                    list.add(records);
                } else if (i == period) {
                    Records records = new Records();
                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal(String.valueOf(equalInterest2));
                    records.setMonthlyInterest("0.00");
                    records.setMonthlyRent("0.00");
                    records.setTotalA(String.valueOf(equalInterest2));
                    list.add(records);
                } else {
                    Records records = new Records();
                    records.setPeriodNumber("第" + i + "期");
                    records.setRepaymentDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                    records.setMonthlyPrincipal(String.valueOf(equalInterest2));
                    records.setMonthlyInterest(String.valueOf(monthlyInterest2));
                    records.setMonthlyRent(String.valueOf(cost));
                    records.setTotalA(String.valueOf((double) Math.round((equalInterest2 + monthlyInterest2 + cost) * 100) / 100));
                    list.add(records);
                }
            }
        }

        return list;

    }
}
