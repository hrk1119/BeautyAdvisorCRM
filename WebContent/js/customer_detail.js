// const saleEdits = document.querySelectorAll('.sale_edit');
// saleEdits.forEach((saleEdit) => {
//     saleEdit.addEventListener('click', () => {
//         saleEdit.dataset.target = "#sale_edit_modal";

//         //モーダルの値
//         const saleId = saleEdit.dataset.saleId; //sale_id → saleId
//         console.log(saleId);

//         //formに値を入れる
//         const saleEditDate = document.querySelector('#sale_edit_date').value;
//         console.log(saleEditDate);
//         const EditDate = document.querySelector(`#sale_date${saleId}`).innerHTML;
//         console.log(EditDate);
//         document.querySelector('#sale_edit_date').value = document.querySelector(`#sale_date${saleId}`).innerHTML;
//         document.querySelector('#sale_edit_store').value = document.querySelector(`#sale_store${saleId}`).innerHTML;
//         document.querySelector('#sale_edit_code').value = document.querySelector(`#sale_code${saleId}`).innerHTML;
//         document.querySelector('#sale_edit_num').value = document.querySelector(`#sale_num${saleId}`).innerHTML;
//     });
// });

//追加ボタン
const btnAdd = document.querySelector('#btn-add');
btnAdd.addEventListener('click', (event) => {
    event.preventDefault();
    const productCodes = document.querySelector('#product_codes');
    const length = document.querySelectorAll('.product_code').length;
    console.log(length);
    const newDiv = document.createElement('div');
    newDiv.classList.add('row', 'mt-3'); //classを追加
    newDiv.innerHTML += `<input type="text" name="product_code${length}" class="form-control col-7 ml-3 product_code validate[custom[onlyLetterNumber]]" placeholder="JANコードを入力してください">`;
    newDiv.innerHTML += `<input type="text" name="num${length}" class="form-control col-2 ml-4 validate[custom[onlyLetterNumber]]" placeholder="個数">`;
    productCodes.appendChild(newDiv);
});

/**タブ**/
//sale
$('.tab-content2>div').hide();
$('.tab-content2>div').first().slideDown();
$('.tab-buttons span').click(function () {
    var thisclass = $(this).attr('class');
    // console.log(thisclass);
    // console.log($('#sale_lamp'));
    $('#sale_lamp').removeClass().addClass('#sale_lamp').addClass(thisclass);
    $('.tab-content2>div').each(function () {
        if ($(this).hasClass(thisclass)) {
            $(this).fadeIn(400);
        }
        else {
            $(this).hide();
        }
    });
});

//supp
$('.tab-content2>div').hide();
$('.tab-content2>div').first().slideDown();
$('.tab-buttons span').click(function () {
    var thisclass = $(this).attr('class');
    // console.log(thisclass);
    // console.log($('#supp_lamp'));
    $('#supp_lamp').removeClass().addClass('#supp_lamp').addClass(thisclass);
    $('.tab-content2>div').each(function () {
        if ($(this).hasClass(thisclass)) {
            $(this).fadeIn(800);
        }
        else {
            $(this).hide();
        }
    });
});