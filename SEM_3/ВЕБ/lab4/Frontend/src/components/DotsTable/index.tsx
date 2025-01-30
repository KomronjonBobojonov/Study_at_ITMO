import React, {useContext} from "react";
import {DotsFormContext} from "../InputFileds/InputFieldContext";

function DotsTable(){
    const context = useContext(DotsFormContext);

    return (
        <table className="w-full text-sm text-left rtl:text-right text-black">
            <thead className="text-md text-center text-gray-700 uppercase">
            <tr>
                <th className="px-5 py-3">X</th>
                <th className="px-5 py-3">Y</th>
                <th className="px-5 py-3">R</th>
                <th className="px-5 py-3">Ответ</th>
                <th className="px-5 py-3 hidden lg:table-cell">Время работы</th>
                <th className="px-5 py-3 hidden lg:table-cell">Текущее время</th>
            </tr>
            </thead>
            <tbody>
            {context && context.getDots.length > 0 && context?.getDots.map((dot, index) => (
                <tr key={index}
                    className="bg-white border-b hover:bg-gray-50 text-center ">
                    <td className="px-5 py-4">{dot.x}</td>
                    <td className="px-5 py-4">{dot.y}</td>
                    <td className="px-5 py-4">{dot.r}</td>
                    <td className={"px-5 py-4 font-semibold uppercase " +
                        (dot.status ? " text-green-700" : " text-red-700")}>{dot.status ? "попал" : "мимо"}</td>
                    <td className="px-5 py-4 hidden lg:table-cell">{dot.scriptTime ? (dot.scriptTime / 1000).toFixed(0) : "..."}</td>
                    <td className="px-5 py-4 hidden lg:table-cell">{dot.time}</td>
                </tr>
            ))}
            </tbody>
        </table>);
}

export default DotsTable;
