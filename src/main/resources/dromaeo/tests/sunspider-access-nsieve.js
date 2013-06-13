var startTest = function(){};
var test = function(name, fn){ fn(); };
var endTest = function(){};
var prep = function(fn){ fn(); };
// The Great Computer Language Shootout
// http://shootout.alioth.debian.org/
//
// modified by Isaac Gouy

function pad(number,width){
   var s = number.toString();
   var prefixWidth = width - s.length;
   if (prefixWidth>0){
      for (var i=1; i<=prefixWidth; i++) s = " " + s;
   }
   return s;
}

function nsieve(m, isPrime){
   var i, k, count;

   for (i=2; i<=m; i++) { isPrime[i] = true; }
   count = 0;

   for (i=2; i<=m; i++){
      if (isPrime[i]) {
         for (k=i+i; k<=m; k+=i) isPrime[k] = false;
         count++;
      }
   }
   return count;
}

startTest("sunspider-access-nsieve");

test( "N-Sieve", function(){
	for ( var i = 1; i <= 2; i++ ) {
       	var m = (1<<i)*10000;
       	var flags = Array(m+1);
	nsieve(m,flags);
	}
});

endTest();
