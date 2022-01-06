package com.keio.automapay.util

import android.content.Context
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.*

object DummyData {

    fun getDummyUtilityBills(): List<UtilityBillsModel>{
        val listUtility = ArrayList<UtilityBillsModel>()
        val data1 = UtilityBillsModel(
            id = 0,
            title = "Electricity",
            date = "02/02/2022",
            cost = "30000",
            image = R.drawable.electiricity
        )
        listUtility.add(data1)
        val data2 = UtilityBillsModel(
            id = 1,
            title = "Water",
            date = "02/02/2022",
            cost = "13000",
            image = R.drawable.water
        )
        listUtility.add(data2)
        val data3 = UtilityBillsModel(
            id = 3,
            title = "Gas",
            date = "02/02/2022",
            cost = "16000",
            image = R.drawable.gas
        )
        listUtility.add(data3)
        return listUtility
    }

    fun getDummySplitBills(codeBills: String): SplitBillModel?{
        val code = "automapay12122021"
        val listOrder = ArrayList<OrderModel>()
        val order1 = OrderModel(
            name = "Ramen",
            totalOrder = 4,
            cost = 1500
        )
        val order2 = OrderModel(
            name = "Kobe Beef Tenppayaki",
            totalOrder = 3,
            cost = 7500
        )
        val order3 = OrderModel(
            name = "Orange Juice",
            totalOrder = 2,
            cost = 800
        )
        val order4 = OrderModel(
            name = "Gyoza",
            totalOrder = 4,
            cost = 600
        )
        listOrder.add(order1)
        listOrder.add(order2)
        listOrder.add(order3)
        listOrder.add(order4)
        return if (codeBills == code) {
            SplitBillModel(
                id = 1,
                title = "Automa Restaurant",
                listOrder = listOrder,
                manyPerson = 4,
                date = "01/01/2021"
            )
        } else {
            null
        }
    }

    fun getDummyExpenditure(): List<ExpenditureModel>{
        val listExpenditure = ArrayList<ExpenditureModel>()
        val data1 = ExpenditureModel(
            id = 1,
            name = "Vuitton Jacket",
            category = "Fashion",
            place = "Tokyo",
            cost = "30000",
            date = "31/12/2021"
        )
        listExpenditure.add(data1)
        val data2 = ExpenditureModel(
            id = 2,
            name = "Vacation To Bali",
            category = "Vacation",
            place = "Bali",
            cost = "200000",
            date = "31/12/2021"
        )
        listExpenditure.add(data2)
        val data3 = ExpenditureModel(
            id = 3,
            name = "Watch Spiderman No-Way Home",
            category = "Entertainment",
            place = "Tokyo",
            cost = "3000",
            date = "31/12/2021"
        )
        listExpenditure.add(data3)

        val data4 = ExpenditureModel(
            id = 4,
            name = "Kobe Beef Tenppayaki",
            category = "Food",
            place = "Tokyo",
            cost = "10000",
            date = "31/12/2021"
        )
        listExpenditure.add(data4)
        return listExpenditure
    }

    fun getCarouselList(context: Context): List<CarouselModel>{
        val listCarousel = ArrayList<CarouselModel>()
        val data1 = CarouselModel(
            image = context.resources.getDrawable(R.drawable.bills),
            title = context.resources.getString(R.string.title1),
            subtitle = context.resources.getString(R.string.subtitle1)
        )
        listCarousel.add(data1)
        val data2 = CarouselModel(
            image = context.resources.getDrawable(R.drawable.spending_history),
            title = context.resources.getString(R.string.title2),
            subtitle = context.resources.getString(R.string.subtitle2)
        )
        listCarousel.add(data2)
        val data3 = CarouselModel(
            image = context.resources.getDrawable(R.drawable.spliting_bills),
            title = context.resources.getString(R.string.title3),
            subtitle = context.resources.getString(R.string.subtitle3)
        )
        listCarousel.add(data3)
        return listCarousel
    }

    fun getBanner(context: Context): List<BannerModel>{
        val listBanner = ArrayList<BannerModel>()
        val bannerModel1 = BannerModel(
            1,
            context.resources.getDrawable(R.drawable.promotion1)
        )
        listBanner.add(bannerModel1)
        val bannerModel2 = BannerModel(
            2,
            context.resources.getDrawable(R.drawable.promotion2)
        )
        listBanner.add(bannerModel2)
        val bannerModel3 = BannerModel(
            3,
            context.resources.getDrawable(R.drawable.promotion3)
        )
        listBanner.add(bannerModel3)
        val bannerModel4 = BannerModel(
            4,
            context.resources.getDrawable(R.drawable.promotion4)
        )
        listBanner.add(bannerModel4)
        val bannerModel5 = BannerModel(
            5,
            context.resources.getDrawable(R.drawable.promotion5)
        )
        listBanner.add(bannerModel5)
        return listBanner
    }
}