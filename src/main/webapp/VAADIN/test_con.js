//window.onload = (function (){
//alert("Привеееееееед!!!");
//if (document.getElementById("protocol")!=null)
//{
//alert(document.getElementById("protocol").value);
//}
//});

//  $(window).on("load",function() {
//    alert( "DOMContentLoaded" );
//    document.addEventListener("DOMContentLoaded", function() {
//      test_con();
//    });
//   if (document.getElementById("protocol")!=null)
//   {
//   alert(document.getElementById("protocol").value);
//   }
//  });
//    document.getElementById('protocol').change;
  function test_con()
  {
//  document.getElementById("protocol").value = "254254";
  //alert(document.getElementById("protocol").value);
        var _input = document.getElementById("protocol");
        var _save_button = document.getElementById("save_button");
//        var _tag_element = document.getElementsByTagName('protocol');
//        var _tag_element = document.getElementById('protocol');

//        console.log(_tag_element[0].value);
        _input.addEventListener("focusout" , function() {
          // alert("Фокус потерян");
          if (_input.value.length < 14) {
          $(_input).css('background-color', '#FA1B35');
           _save_button.style.visibility = 'hidden';      // Hide
//            element.style.visibility = 'visible';
//          _save_button.className += "  v-disabled";
//          _save_button.setAttribute('aria-disabled', true);
//          _save_button.setAttribute('tabindex', -1);
//          _save_button.innerHTML = _save_button.innerHTML + 'Extra stuff';

//          aria-disabled="true"
          }
          else
          {
          _save_button.style.visibility = 'visible';
          }


        });

        _input.addEventListener("focus" , function() {
          // alert("Фокус потерян");
          $(_input).css('background-color', '#FFF');
//          document.getElementById("save_button").disabled =  false;
        });

        _input.addEventListener("keydown" , function () {
          var key = event.keyCode || event.charCode;

          //дать возможность стиреть значение из поля
          if( key == 8 || key == 46 || key == 39 || key == 38 || key == 37 || key == 40 )
          {return false};

          var _output="";
          var _output_slash="";
          _output_slash = _input.value;

          if (_input.value.length > 13) {
          _input.value = _input.value.substring(0, 13);
          }


          if (_output_slash.length == 5)
          {
            _output_slash = _input.value + '/';

            _input.value = _output_slash
          }
          else if (_output_slash.length == 8) {
            _output_slash = _input.value + '/';
            _input.value = _output_slash
          }

          else{
            _output_slash = _input.value;
            _input.value = _output_slash
          }
        })
        sort_grid();
        console.log(_input.value.length);

  }
  function sort_grid()
  {
  var _grid = document.getElementById("search_grid");
//  _grid.columns[0].sortable = true;
//  _grid.sortOrder = [{column: 0, direction: 'desc'}];
  }
  function unfocused()
  {

  }


//<div tabindex="-1" role="button" class="v-button v-widget v-has-width  v-disabled  v-disabled" id="save_button" style="width: 100%;" aria-disabled="true"><span class="v-button-wrap"><span class="v-button-caption">Сохранить данные</span></span></div>
//<div tabindex="0" role="button" class="v-button v-widget v-has-width  v-disabled" id="save_button" style="width: 100%;" aria-disabled="true"><span class="v-button-wrap"><span class="v-button-caption">Сохранить данные</span></span></div>