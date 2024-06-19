import os
import sqlite3 as sql

import asyncio
import logging
from aiogram import Bot, Dispatcher, types, F
from aiogram.filters.command import Command
from aiogram.utils.markdown import link

TOKEN = "6632707680:AAHgALop6GPnllgFruIzRQV2JZZ6P_FLsIg"
DB_FILENAME = "users.db"
GROUP_ID = -1002074269915
ADMIN_IDS = {820393036}
admin_mode = False

logging.basicConfig(level=logging.INFO)

class Database:
    def __init__(self):
        if os.path.exists(DB_FILENAME) and not os.path.isfile(DB_FILENAME):
            exit(print(f"{DB_FILENAME} exists as a directory"))
        self.conn = sql.connect(DB_FILENAME)
        self.cur = self.conn.cursor()
        self.cur.execute("CREATE TABLE IF NOT EXISTS user(user_id INTEGER, UNIQUE(user_id))")

    def add_user(self, user_id):
        self.cur.execute("INSERT OR IGNORE INTO user (user_id) VALUES (?)", (user_id,))
        self.conn.commit()
    
    def get_all_user_ids(self):
        return self.cur.execute("SELECT user_id from user").fetchall()

    def __del__(self):
        self.conn.close()


bot = Bot(token=TOKEN)

dp = Dispatcher()
db = Database()

replies = {"hello": "Привет, дорогая!\nМы рады, что ты выбрала именно нас.❤️\n"
                    "В ответ команда Shady подготовила для тебя несколько подарков!\n\n"
                    "Подскажи пожалуйста, устроило ли тебя качество товара? (ответ ботом да, нет)",
            "feedback:da": "Мы очень рады, что тебе понравились леггинсы нашего бренда!\n"
                           "Позволь подарить тебе несколько классных подарков от искреннего сердца❤️",
            "feedback:net": "Нам очень жаль, что так получилось.\n"
                            "Напиши пожалуйста на аккаунт нашей поддержки и мы обязательно разберемся в ситуации\n"
                            "\n(Аккаунт Венерки в тг.)\n\n"
                            "В качестве извинений, мы подготовили для тебя несколько подарков,"
                            "надеемся они тебе понравятся.",
            "bonus:workouts": "Этот раздел в разработке скоро он откроется, как только это проезойдёт мы дадим тебе знать🤗",
            "bonus:gifts": "По ссылке тебя ждёт подарок✨✨✨ https://flocktory.com/interchange/2258b525-c0ab-44e8-983d-419b49c62640/?utm_medium=referral&utm_source=yoomoney.ru&utm_campaign=exchange)",
            "bonus:instruction": "Дорогие покупательницы, мы улучшаем наше изделие и каждый отзыв важен для нас! Чтобы леггинсы SHADY прослужили вам дольше мы подготовили для вас рекомендацию по уходу за товаром: Используйте бережный режим стирки. Не стоит стирать изделие при высоких температурах и на больших оборотах отжима дабы не повредить ткань. 2. Советуем сушить на плоской поверхности. Ткань леггинс может деформироваться и растянуться от сушки в стиральной машине или в вертикальном положении. Избегайте сушки под солнечными лучами.3. Используйте специальную сетку для бережной стирки. 4. Запрещено использование отбеливателя и химчистка.  Следование этим рекомендациям поможет сохранить ваши леггинсы в отличном состоянии и будут радовать вас долгое время!",
            "spam_cancelled": "Рассылка отменена",
            "admin_mode_on": "Вы вошли в админку. Пришлите сообщение для рассылки",
            "spam_ask": "Вы уверены что хотите начать рассылку?",
            "spam_start": "Начинается рассылка",
            "spam_finished": "Рассылка завершена",
}

@dp.callback_query(F.data.startswith("bonus:"))
async def send_random_value(callback: types.CallbackQuery):
    await callback.message.answer(replies[callback.data])


@dp.callback_query(F.data.startswith("feedback:"))
async def send_random_value(callback: types.CallbackQuery):
    buttons = [[types.InlineKeyboardButton(text="Комплекс домашних тренировок", callback_data="bonus:workouts")],
               [types.InlineKeyboardButton(text="Подарки от наших партнеров", callback_data="bonus:gifts")],
               [types.InlineKeyboardButton(text="Инструкция по уходу", callback_data="bonus:instruction")]]
    keyboard = types.InlineKeyboardMarkup(inline_keyboard=buttons)
    await callback.message.answer(replies[callback.data], reply_markup=keyboard)

@dp.callback_query(F.data.startswith("spam:"))
async def send_random_value(callback: types.CallbackQuery):
    _, status, from_user, msg_id = callback.data.split(":")
    if status == "start":
        users = db.get_all_user_ids()
        for admin_id in ADMIN_IDS:
            try:
                users.remove((admin_id,))
            except Exception:
                pass
        await bot.send_message(from_user, replies["spam_start"])
        for i in range(len(users)):
            user = users[i][0]
            try:
                await bot.copy_message(user, from_user, msg_id)
            except Exception:
                pass
        await bot.send_message(from_user, replies["spam_finished"])
    else:
        await bot.send_message(from_user, replies["spam_cancelled"])

    global admin_mode
    admin_mode = False


@dp.message(Command("start"))
async def cmd_start(message: types.Message):
    db.add_user(message.from_user.id)
    buttons = [[types.InlineKeyboardButton(text="Да", callback_data="feedback:da"),
                types.InlineKeyboardButton(text="Нет", callback_data="feedback:net")]]
    keyboard = types.InlineKeyboardMarkup(inline_keyboard=buttons)
    await message.forward(GROUP_ID)
    await message.answer(replies["hello"], reply_markup=keyboard)


@dp.message(Command("admin"))
async def cmd_start(message: types.Message):
    global admin_mode
    admin_mode = True
    await message.answer(replies["admin_mode_on"])


@dp.message()
async def text_msg_handler(message: types.Message):
    if admin_mode and message.from_user.id in ADMIN_IDS:
        buttons = [[types.InlineKeyboardButton(text="Начать рассылку",
                                               callback_data=f"spam:start:{message.from_user.id}:{message.message_id}"),
                    types.InlineKeyboardButton(text="Галя, у нас отмена",
                                               callback_data=f"spam:cancel:{message.from_user.id}:{message.message_id}")]]
        keyboard = types.InlineKeyboardMarkup(inline_keyboard=buttons)
        await message.answer(replies["spam_ask"], reply_markup=keyboard)
    elif message.chat.type == "private":
        await message.forward(GROUP_ID)


async def main():
    await dp.start_polling(bot)


if __name__ == "__main__":
    asyncio.run(main())
